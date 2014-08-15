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

include $_SERVER['DOCUMENT_ROOT'].'/route.php';
include $root.Controller::$Client;
include $root.Model::$Travel;
include $root.Model::$TravelWithDriver;
$TRAVEL_DEVIATION_KM = 10;


if(!empty($_POST['travel_advertiser']) &&
   !empty($_POST['travel_origin']) &&
   !empty($_POST['travel_destination']) &&
   !empty($_POST['travel_date']) &&
   !empty($_POST['travel_time'])){
    
    $travel_advertiser = $_POST['travel_advertiser'];
    
    if(strcmp($travel_advertiser, "passenger") == 0){
        $travel = new Travel();
        $travel -> origin = $_POST['travel_origin'];
        $travel -> destination = $_POST['travel_destination'];
        $travel -> date = $_POST['travel_date'];
        $travel -> time = $_POST['travel_time'];
        
        $client = new Client($host);
        $client -> addRequestedTravel($travel);
        ?>
        <script languaje="javascript">
            location.href = "<?= Address::$MyTravels ?>";
        </script>
        <?php

    }else{
        if(!empty($_POST['travel_space']) &&
           !empty($_POST['travel_car']) &&
           !empty($_POST['travel_terms'])){

            
            $client = new Client($host);
            $travel = $client ->getInfoTravel($_POST['travel_origin'], $_POST['travel_destination'], $TRAVEL_DEVIATION_KM);
            $travel -> date = $_POST['travel_date'];
            $travel -> time = $_POST['travel_time'];
            $travel -> car = $_POST['travel_car'];
            $travel -> space = $_POST['travel_space'];
            ?>
            <script type="text/javascript">
                document.write("<form id='add_travel' action='/publish_travel' method='post'>\n\
                    <input type='hidden' name='travel' value='<?=str_replace("\"","\\\"",json_encode($travel))?>'>\n\
                    </form>");
                document.forms["add_travel"].submit();
            </script>
            <?php

        }else{
            ?>
            <script languaje="javascript">
                location.href = "/page_dont_found";
            </script>
            <?php
        }
    }
    
}else{
    ?>
    <script languaje="javascript">
        location.href = "/page_dont_found";
    </script>
    <?php
}
exit();