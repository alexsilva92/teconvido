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

include_once $root.Controller::$Client;

$client = new Client($host);

$travels = $client ->getTravelsWithDriverUser();

if(count($travels) > 0){

    echo "<table class='table table-condensed'>";
    echo"<thead> <th>#</th> <th>Viaje</th> <th>Fecha de Creación</th> </thead>";
      
    $i = 1;
    foreach ($travels as $travel) {
        echo "<tr>";
        echo "<td>".$i."</td>";
        $date = strtotime($travel -> dateTravel);
        echo "<td><a href''>".date("d/m/y",$date)." ".$travel -> origin." - ".$travel -> destination."</a></td>";
        $date = strtotime($travel -> dateCreation);
        echo "<td>".date("d/m/y",$date)."</td>";
        echo "</tr>";
        $i = $i + 1; 
    }
    
    echo "</table>";
}else{
    echo "<h5>No has publicado ningún viaje como conductor</h5>";
}
?>