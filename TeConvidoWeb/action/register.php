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
include_once $root.Model::$User;
include $root.Module::$CheckNotSession;

if(!empty($_POST['reg_login']) &&
   !empty($_POST['reg_password']) &&
   !empty($_POST['reg_name']) &&
   !empty($_POST['reg_subname']) &&
   !empty($_POST['reg_email'])){

    $user = new User();
    $user -> login = $_POST['reg_login'];
    $user -> password = $_POST['reg_password'];
    $user -> name = $_POST['reg_name'];
    $user -> subname = $_POST['reg_subname'];
    $user -> email = $_POST['reg_email'];
    
    $client = new Client("localhost");
    
    $add = $client ->addUser($user);
    
    unset($client);
    
    if($add != NULL){
        ?>
        <script languaje="javascript">
            location.href = "/";
        </script>
        <?php
    }else{
        ?>
        <form id='login' action='/register' method='post'>
            <input type='hidden' name='error' value='2'>
            <input type='hidden' name='reg_login' value='<?php echo $user -> login?>'>
            <input type='hidden' name='reg_name' value='<?php echo $user -> name?>'>
            <input type='hidden' name='reg_subname' value='<?php echo $user -> subname?>'>
            <input type='hidden' name='reg_email' value='<?php echo $user -> email?>'>
        </form>
        <script languaje="javascript">
            document.forms["login"].submit();
        </script>
        <?php
    }
}else{
    ?>
    <script languaje="javascript">
        location.href = "/page_dont_found";
    </script>
    <?php
}
exit();