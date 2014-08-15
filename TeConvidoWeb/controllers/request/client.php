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

include_once $root.Controller::$UrlRequest;
include_once $root.Controller::$DBRoute;
include_once $root.Controller::$Session;
include_once $root.Model::$Url;
include_once $root.Model::$User;
include_once $root.Model::$TravelStop;


class Client{

    public static $SEARCH = "search=";
    public static $ELEMENT = "element=";
    private $host;
    
    function __construct($host){
        $this -> host = $host;
    }
/******************************** GET *****************************************/        
    function isCorrectLogin($email,$password){
        $user = new User();
        $user -> email = $email;
        $user -> password = $password;
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host,Get::$IS_CORRECT_LOGIN),
           Client::$SEARCH.json_encode($user));
        
        return json_decode($response);
    }
    
    function getInfoTravel($origin,$destination,$deviation){
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
    
    function getCarsUser(){
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host,Get::$GET_CARS_USER),NULL,  
                json_encode(getTicketDB()));
        return json_decode($response);
    }
    
    function getRequestedTravelsUser(){
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host,Get::$GET_REQUESTED_TRAVELS_USER),NULL,  
                json_encode(getTicketDB()));
        return json_decode($response);
    }
    
    function getTravelsWithDriverUser(){
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host,Get::$GET_TRAVELS_WITH_DRIVER_USER),NULL,  
                json_encode(getTicketDB()));
        return json_decode($response);
    }
    
    function getRealizedTravelsUser(){
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host,Get::$GET_REALIZED_TRAVELS_USER),NULL,  
                json_encode(getTicketDB()));
        return json_decode($response);
    }
    
    function getCar($registration){
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host,Get::$GET_CAR),
           Client::$SEARCH.json_encode($registration),json_encode(getTicketDB()));
        return json_decode($response);
    }
    
/******************************************************************************/  
/******************************* INSERT ****************************************/   
    
    function addUser($user){
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host, Insert::$ADD_USER),
           Client::$ELEMENT.json_encode($user));
        return json_decode($response); 
    }
    
    function addRequestedTravel($travel){
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host, Insert::$ADD_REQUESTED_TRAVEL),
           Client::$ELEMENT.json_encode($travel),json_encode(getTicketDB()));
        echo $response;
        //return json_decode($response); 
    }
    
    function addTravelWithDriver($travel){
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host, Insert::$ADD_TRAVEL_WITH_DRIVER),
           Client::$ELEMENT.json_encode($travel),json_encode(getTicketDB()));
        return $response;
        //return json_decode($response); 
    }
    
    function addTravelStop($travel,$stop,$position){
        $travelStop = new TravelStop();
        $travelStop -> travel = $travel;
        echo "***".$travelStop -> travel."*".$travel."**";
        $travelStop -> stop = $stop;
        $travelStop -> position = $position;
        $response = postRequest(
           new Url(Url::$DEFAULT_PROTOCOL,$this -> host, Insert::$ADD_TRAVEL_STOP),
           Client::$ELEMENT.json_encode($travelStop),json_encode(getTicketDB()));
        echo $response;
        //return json_decode($response); 
    }
    
/******************************************************************************/  
}