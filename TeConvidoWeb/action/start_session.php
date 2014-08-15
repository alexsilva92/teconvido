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
include $root.Controller::$Session;
include $root.Controller::$Client;
include $root.Module::$CheckNotSession;

if(!empty($_POST['nav_email']) && !empty($_POST['nav_password']) ){

    $email = $_POST['nav_email'];
    $password = $_POST['nav_password'];

    $client = new Client("localhost");
    $ticket = $client->isCorrectLogin($email, $password);
    unset($client);

    if($ticket != NULL){
        createSession($ticket -> login,$ticket -> ticket);
        
        ?>
        <script languaje="javascript">
            location.href = "/my_profile";
        </script>
        <?php
    }else{
        ?>
        <script type="text/javascript">
            document.write("<form id='login' action='/' method='post'>\n\
                <input type='hidden' name='error' value='1'></form>");
            document.forms["login"].submit();
        </script>
        <?php
    }
} else{
    ?>
    <script languaje="javascript">
        location.href = "/page_dont_found";
    </script>
    <?php
}
exit();