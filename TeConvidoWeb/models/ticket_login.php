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
 * TicketLogin.php
 * @author Alejandro Silva <alexsilva792@gmail.com>
 */
class TicketLogin implements JsonSerializable {
    private $login;
    private $ticket;
    
    public function __set($var, $value) {
        $var = strtolower($var);
        if (property_exists('TicketLogin',$var)) {
            $this->$var = $value;
        }
    }
 
    public function __get($var){
        $var = strtolower($var);
        if (property_exists('TicketLogin', $var)){
            return $this->$var;
        }
 
        return NULL;
    }

    public function jsonSerialize() {
        return [
            'login' => $this->login,
            'ticket' => $this->ticket
        ];
    }
}