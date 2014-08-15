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

$login = isCorrectAuthorization();
if($login != NULL){
    if(!empty($_POST['search'])){
        $search = json_decode($_POST['search']);
        
        $sql = "SELECT c.carRegistration,c.brand,c.model,c.color,c.description,o.owner ".
               "FROM Car c,CarOwner o ".
               "WHERE c.carRegistration = o.car AND c.carRegistration = :registration";

        $db = connect($host, $user, $password, $database);
        if($db != NULL){
            $ps = $db->prepare($sql);
            $ps ->bindParam(':registration', $search);
            $ps->execute();
            $result = $ps->fetch(PDO::FETCH_OBJ);
            echo json_encode($result);

            unset($ps);
            unset($db);
        }else{
            http_response_code(500);
        }
    }else{
        http_response_code(412);
    }
}else{
   http_response_code(401);
}