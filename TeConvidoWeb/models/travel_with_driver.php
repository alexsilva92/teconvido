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

include_once $_SERVER["DOCUMENT_ROOT"].'/route.php';
include_once $root.Model::$Travel;

class TravelWithDriver extends Travel{
    private $driver;
    private $car;
    private $price;
    private $state;
    private $space;
    private $travelStops = array();
    
    function __construct(){
        $this -> travelStops = array();
    }
    
    public function __set($var, $value) {
        if (property_exists('TravelWithDriver',$var) || property_exists('Travel', $var)) {
            $this -> $var = $value;
        }
    }
 
    public function __get($var){
        if (property_exists('TravelWithDriver', $var)){
            return $this -> $var;
        }else if(property_exists('Travel', $var)){
            return $this -> $var;
        }
 
        return NULL;
    }
    
    public function jsonSerialize() {
        return [
            'id' => $this -> id,
            'origin' => $this -> origin,
            'destination' => $this -> destination,
            'date' => $this -> date,
            'time' => $this -> time,
            'dateCreation' => $this -> dateCreation,
            'driver' => $this -> driver,
            'car' => $this -> car,
            'price' => $this -> price,
            'state' => $this -> state,
            'space' => $this -> space,
            'travelStops' => $this -> travelStops,
            'distance' => $this -> distance,
            'duration' => $this -> duration
        ];
    }
}