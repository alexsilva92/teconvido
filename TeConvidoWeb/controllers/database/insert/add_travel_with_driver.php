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
    if(!empty($_POST['element'])){

        $sql = "CALL teconvido.addTravelWithDriver(:origin,:destination,:dateTravel,:dateCreation,:distance,:duration,:car,:space,:price,:state,:author);";

        $element = json_decode($_POST['element']);

        $db = connect($host, $user, $password, $database);
        if($db != NULL){
            $ps = $db->prepare($sql);
            $ps ->bindParam(':origin', $element -> origin);
            $ps ->bindParam(':destination', $element -> destination);
            $date = $element -> date." ".$element -> time;
            $ps ->bindParam(':dateTravel', $date);
            $dateCreation =  new DateTime();
            $ps ->bindParam(':dateCreation',$dateCreation -> format("Y-m-d H:i:s"));
            $ps ->bindParam(':distance', $element -> distance);
            $ps ->bindParam(':duration', $element -> duration);
            $pos = strpos($element -> car, " ");
            $element -> car = substr($element -> car,0,$pos);
            $ps ->bindParam(':car', $element -> car);
            $ps ->bindParam(':space', $element -> space);
            $ps ->bindParam(':price', $element -> price);
            $ps ->bindParam(':author', $login);
            $state = "ABIERTO";
            $ps ->bindParam(':state',$state);
            $ps->execute();

            $result = $ps->fetch(PDO::FETCH_OBJ);
        
            if($result != NULL){
                echo $result -> id;
            }else{
                echo NULL;
            }
            
            unset($ps);
            unset($db);
        }else {
            http_response_code(500);
        }
    } else {
    http_response_code(412);
    }
}else {
   http_response_code(401);
}