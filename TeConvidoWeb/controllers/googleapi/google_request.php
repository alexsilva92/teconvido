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
include_once $root.Controller::$UrlRequest;
include_once $root.Model::$TravelWithDriver;
include_once $root.Model::$Url;
include_once $root.Controller::$DBConfig;
include_once $root.Controller::$DBConnect;
include_once $root.Controller::$DBRoute;
include_once $root.Controller::$Session;

if(!empty($_POST['origin']) && !empty($_POST['destination'])
   && !empty($_POST['deviation'])){
    $origin = $_POST['origin'];
    $destination = $_POST['destination'];
    $deviation = $_POST['deviation'];
    
    $url = "http://maps.googleapis.com/maps/api/directions/json";
    $parameter = "origin=". str_replace(" ","+",$origin) .
                 "&destination=". str_replace(" ","+",$destination) . 
                 "&region=es&sensor=false";
    
    $response = json_decode(requestMethodGet($url, $parameter));

    if($response != NULL && strcmp($response -> status, "OK") == 0){
        $travel = new TravelWithDriver();
        $travel -> origin = $origin;
        $travel -> destination = $destination;
        $travel -> distance = 
                $response -> routes[0] -> legs[0] -> distance -> text;
        $travel -> duration = 
                $response -> routes[0] -> legs[0] -> duration -> text;

        $towns = array();
        foreach ($response -> routes[0] -> legs[0] -> steps as $step){
            $townsRequest = json_decode(postRequest(
                    new Url(Url::$DEFAULT_PROTOCOL,"localhost",Get::$GET_TOWNS_AROUND),
                    "longitude=".$step -> start_location -> lng.
                    "&latitude=".$step -> start_location -> lat.
                    "&deviation=".$deviation));
            
            foreach($townsRequest as $town){
                if(!strcmp($origin, $town -> name) == 0 && !strcmp($destination, $town -> name)  == 0
                        && !in_array($town -> name,$towns)){
                    array_push ($towns, $town -> name);  
                }
            }
        }
        
        $travel -> travelStops = $towns;

        echo json_encode($travel);      

    }else{
        http_response_code(412);
    }
} else{
    http_response_code(412);
}