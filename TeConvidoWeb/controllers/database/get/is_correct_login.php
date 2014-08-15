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

include $_SERVER["DOCUMENT_ROOT"].'/route.php';
include_once $root.Controller::$DBConfig;
include_once $root.Controller::$DBConnect;
include_once $root.Controller::$Session;
include_once $root.Model::$User;
include_once $root.Model::$TicketLogin;

if(!empty($_POST['search'])){
    
    $sql = "SELECT u.login FROM User u WHERE u.email = :email AND "
    . "u.password = sha1(:password)";
    
    $search= json_decode($_POST['search']);
    
    $db = connect($host, $user, $password, $database);
    if($db != NULL){
        
        $ps = $db->prepare($sql);
        $ps ->bindParam(':email', $search -> email);
        $ps->bindParam(':password', $search -> password); 
        
        $ps->execute();
        $result = $ps->fetch(PDO::FETCH_OBJ);
        
        if($result != NULL){
            $ticket = new TicketLogin();
            $ticket -> login = $result -> login;
            $ticket -> ticket = createSession($result -> login);
            echo json_encode($ticket);   
        }else{
            echo NULL;
        }
        
        unset($ps);
        unset($db);
    }else{
        http_response_code(500);
    }
} else{
    http_response_code(412);
}