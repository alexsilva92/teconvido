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

function postRequest($url,$parameter,$authorization = \NULL){
    $session = curl_init($url);

    if($session != NULL){
        curl_setopt ($session, CURLOPT_POST, true); 
        curl_setopt ($session, CURLOPT_POSTFIELDS,$parameter); 
        curl_setopt($session, CURLOPT_HEADER, false); 
        curl_setopt($session, CURLOPT_RETURNTRANSFER, true);
        if($authorization != NULL){
            curl_setopt($session, CURLOPT_HTTPHEADER, 
                    array("Authorization: ".$authorization));
        }

        $response = curl_exec($session); 

        curl_close($session); 
        return $response;
    }
    return NULL;
}

function requestMethodGet($url,$parameter){
    $session = curl_init($url);

    if($session != NULL){
        curl_setopt($session,CURLOPT_URL,$url."?".$parameter);
        curl_setopt($session, CURLOPT_HEADER, false); 
        curl_setopt($session, CURLOPT_RETURNTRANSFER, true);
        
        $response = curl_exec($session); 

        curl_close($session); 
        return $response;
    }
    return NULL;
}