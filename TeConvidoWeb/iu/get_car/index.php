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

$path = __DIR__;

include $path.'/route.php';
include $path.ControllerGetCar::$GetCar;
include $root.Module::$CheckSession;
include_once $root.Controller::$Client;

if(empty($_GET["registration"])){
    ?>
    <script languaje="javascript">
        location.href = "/page_dont_found";
    </script>
    <?php
}else{
    $client = new Client($host);
    $car = $client ->getCar($_GET["registration"]);
    $controller = new GetCar($path,$car);
}
