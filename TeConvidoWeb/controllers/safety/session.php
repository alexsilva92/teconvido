<?php

/* 
 * Copyright 2014 Alejandro Silva <alexsilva792@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

function startSession(){
    if(!isset($_SESSION["on"])){
        session_name("teconvido");
        session_start();
        $_SESSION["on"] = TRUE;
    }
}

function createSession($login,$ticketDB = NULL){
    startSession(); 

    $_SESSION["login"] = $login;
    if($ticketDB != NULL){
        $_SESSION["ticketDB"] = $ticketDB;
    }

    return session_id();
}

function getSession(){
    startSession();
    
    $login = NULL;
    
    if (isset($_SESSION["login"])){
        $login = $_SESSION["login"];   
    }
    
    return $login;
}

function getTicketDB(){
    startSession();
    
    $ticketDB = NULL;
    
    if (isset($_SESSION["ticketDB"])){
        $ticketDB = $_SESSION["ticketDB"];   
    }
    
    return $ticketDB;
}

function closeSession($ticket = NULL){
    if($ticket != NULL){
        session_id($ticket);
    }
    
    startSession();
    
    unset($_SESSION["login"]);
    unset($_SESSION["ticketDB"]);
    unset($_SESSION["on"]);
    
    session_destroy();
}

function isCorrectAuthorization(){  
    $headers = apache_request_headers();

    $ticket = NULL;

    foreach ($headers as $header => $value) {
        if(strcmp($header, "Authorization") == 0){
            $ticket = json_decode($value);
        }
    }
    if($ticket != null){
        session_id($ticket);
        return getSession();
    }else{
       return NULL;
    }
}



