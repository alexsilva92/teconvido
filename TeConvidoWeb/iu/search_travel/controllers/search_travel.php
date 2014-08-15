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

class SearchTravel{
    
    private static $TAG_TITLE = "Buscar Viaje";
    private static $TAG_ORIGIN = "origin";
    private static $TAG_DESTINATION = "destination";
    private static $TAG_DATE = "date";
    private static $TAG_TYPE_ADVERTIRSER= "type_advertiser";
    
    private $path;
    private $antsRoad;
    
    private $correctDate;
    private $correctOrigin;
    private $correctDestination;
    private $correctParameters;
    

    function __construct($path,$towns) {
        $this -> path = $path;
        $this -> towns = $towns;
        $this -> antsRoad = array (SearchTravel::$TAG_TITLE => Address::$SearchTravel);
        $this -> initializeError();
    }
    
    function includeBody($root,$host){
        
        include $this -> path.ViewSearchTravel::$SearchPanel;
        
        if($this -> correctParameters && $this -> correctOrigin && 
           $this -> correctDestination && $this -> correctDate){
            include $this -> path.ViewSearchTravel::$ResultPanel;
        }
    }
    
    function getAntsRoad(){
        return $this -> antsRoad;
    }
    
    private function initializeError(){
        if(Utilities::GET(SearchTravel::$TAG_DATE) == NULL &&
           Utilities::GET(SearchTravel::$TAG_ORIGIN) == NULL &&
           Utilities::GET(SearchTravel::$TAG_DESTINATION) == NULL &&
           Utilities::GET(SearchTravel::$TAG_TYPE_ADVERTIRSER) == NULL ){
            
            $this -> correctDate = TRUE;
            $this -> correctOrigin = TRUE;
            $this -> correctDestination = TRUE;
            
        }else{
            $this -> correctParameters = TRUE;
            
            $this -> correctDate = Utilities::checkDate(Utilities::GET(
                SearchTravel::$TAG_DATE)) == TRUE;
        
            $this -> correctOrigin = in_array(Utilities::GET(
                    SearchTravel::$TAG_ORIGIN), $this -> towns);

            $this -> correctDestination= in_array(Utilities::GET(
                    SearchTravel::$TAG_DESTINATION), $this -> towns);
        }          
    }
    
    private function showError(){
        if(!$this -> correctOrigin || !$this -> correctDestination || !$this -> correctDate){
            include $this -> path.ViewSearchTravel::$MessageError;
        }
    }
}

