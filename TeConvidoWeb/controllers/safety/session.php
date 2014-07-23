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

function startSession($login){
    session_name("teconvido");
    session_start(); 

    $_SESSION["login"] = $login;

    return session_id();
}

function isCorrectAuthorization(){  
    $headers = apache_request_headers();

    $ticket = NULL;

    foreach ($headers as $header => $value) {
        if(strcmp($header, "Authorization") == 0){
            $ticket = json_decode($value);
        }
    }
    session_id($ticket -> ticket);

    return isSession();
}

function setIdSession($ticket){
    session_id($ticket);
    session_name("teconvido");
    session_start();
}

function isSession($create = TRUE){
    if($create){
        session_name("teconvido");
        session_start();
    }
    
    if (isset($_SESSION["login"])){
        return $_SESSION["login"];
    }
    return NULL;
}
function closeSession($ticket = NULL){
    if($ticket == NULL){
        session_id();
    }else{
        session_id($ticket);
    }
    session_name("teconvido");
    session_start();
    
    unset($_SESSION["login"]);
    session_destroy();
}