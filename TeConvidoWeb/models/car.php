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

/**
 * User.php
 * @author Alejandro Silva <alexsilva792@gmail.com>
 */
class Car implements JsonSerializable{
    private $carRegistration;
    private $brand;
    private $model;
    private $color;
    private $description;
    private $owner;
    
    public function __set($var, $value) {
        $var = strtolower($var);
        if (property_exists('Car',$var)) {
            $this -> $var = $value;
        }
    }
 
    public function __get($var){
        $var = strtolower($var);
        if (property_exists('Car', $var)){
            return $this -> $var;
        }
 
        return NULL;
    }

    public function jsonSerialize() {
        return [
            'carRegistration' => $this -> carRegistration,
            'brand' => $this -> brand,
            'model' => $this -> model,
            'color' => $this -> color,
            'owner' => $this -> owner,
            'description' => $this -> description
        ];
    }
    
    public function __toString(){
        return $this -> carRegistration." ".$this -> brand." ".$this -> model;
    }

}