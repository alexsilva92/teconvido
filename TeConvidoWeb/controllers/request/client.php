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

include $root.Controller::$UrlRequest;
include $root.Controller::$DBRoute;
include $root.Model::$User;
include $root.Model::$Url;
include $root.Model::$GoogleTravel;


class Client{

    public static $SEARCH = "search=";
    public static $ELEMENT = "element=";
    private $host;
    
    function __construct($host){
        $this -> host = $host;
    }
    
    function isCorrectLogin($email,$password){
        $user = new User();
        $user -> email = $email;
        $user -> password = $password;
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host,Get::$IS_CORRECT_LOGIN),
           Client::$SEARCH.json_encode($user));
        return json_decode($response);
    }
    
    function getGoogleTravel($origin,$destination,$deviation){
        $parameter = "origin=".$origin."&destination=".$destination."&deviation=".$deviation;

        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host,Controller::$GoogleRequest),
           $parameter);

        return json_decode($response);
    }
    
    function getTowns(){
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host,Get::$GET_TOWNS),NULL);
        return json_decode($response);
    }
    
    function addUser($user){
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host, Insert::$ADD_USER),
           Client::$ELEMENT.json_encode($user));
        return json_decode($response); 
    }
}