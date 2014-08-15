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

?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <?php include $_SERVER["DOCUMENT_ROOT"].'/route.php'; ?>
    <?php include $root.View::$Head; ?>
    <link href="/public//css/bootstrap-dialog.css" rel="stylesheet" type="text/css">
    <script src="/public/js/bootstrap-dialog.min.js"></script>
    <?php include $root.View::$Dialog; ?>
    <?php include $root.Module::$CheckNotSession; ?>
    <?php static $ERROR_NOT_REGISTER = 1;?>
    <?php static $ERROR_LOGIN_OR_EMAIL_EXITS = 2;?>
    
    <?php $antsRoad = array ("Registrar" => Address::$Register);?>
  </head>

  <body>
    <?php include $root.View::$Navbar; ?>
    <div class="container">
        <div class="container-teconvido">
            
            <?php include $root.Module::$AntsRoad?>
            
            <div class="row">
              <div class="col-xs-6">
                  <?php include $root.View::$RegistrationPanel ?>
              </div>

              <div class="col-xs-6">
                  <?php include $root.View::$LoginPanel ?>  
              </div>

              <div class="clearfix visible-xs"></div>
            </div>
        </div>
    </div>
    <?php
    if(isset($_POST['error'])) {
        $error = $_POST['error'];
        if($error == $ERROR_NOT_REGISTER){
            showDangerDialog("Acceso denegado", "Tienes que estar logueado para acceder a esa zona");
        }else if($error == $ERROR_LOGIN_OR_EMAIL_EXITS){
            showDangerDialog("Registro incorrecto", "El login o el email ya existen");
        }
    }
    ?>  
    <?php include $root.View::$Footer; ?>
  </body>
</html>