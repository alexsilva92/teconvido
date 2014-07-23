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
include $root.Controller::$DBConfig;
include $root.Controller::$DBConnect;
include $root.Controller::$Session;

function existUser($db,$login,$email){
    $sql = "SELECT * FROM User u WHERE u.email = :email OR "
    . "u.login = :login";
    
    $ps = $db->prepare($sql);
    $ps ->bindParam(':email', $email);
    $ps->bindParam(':login', $login); 

    $ps->execute();
    $result = $ps->fetch(PDO::FETCH_OBJ);
    if($result != NULL){
        return TRUE;
    }else{
        return FALSE;
    }
}

if(!empty($_POST['element'])){

    $sql = "INSERT INTO User(login,email,password,name,subname) "
           ."VALUES(:login,:email,sha1(:password),:name,:subname)";

    $element = json_decode($_POST['element']);
    $login = $element -> login;
    $email = $element -> email;
    $pass = $element -> password;
    $name = $element -> name;
    $subname = $element -> subname;
    
    $db = connect($host, $user, $password, $database);
    if($db != NULL){
        if(!existUser($db,$login,$email)){
            $ps = $db->prepare($sql);
            $ps ->bindParam(':login', $login);
            $ps ->bindParam(':email', $email);
            $ps ->bindParam(':password', $pass);
            $ps ->bindParam(':name', $name);
            $ps ->bindParam(':subname', $subname);
            $ps->execute();

            unset($ps);
            unset($db);
            echo json_encode(TRUE);
        }else{
            echo json_encode(FALSE);
        }
    }else{
        http_response_code(500);
    }
} else{
    http_response_code(412);
}
