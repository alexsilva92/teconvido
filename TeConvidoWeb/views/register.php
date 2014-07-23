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
  </head>

  <body>
    <?php include $root.View::$Navbar; ?>
    <br><br><br>
    <div class="container">
      <div class="row">
        <div class="col-xs-6">
            <div class="register">
                <div class="panel panel-primary ">

                    <div class="panel-heading">
                    <h3 class="panel-title">Registro</h3>
                    </div>

                    <div class="panel-body">
                       <form role="form" method="post" action="/modules/login/login.php">

                           <div class="form-group">
                               <input name="reg_login" type="text" placeholder="Login" class="form-control" required>
                           </div> 

                           <div class="form-group">
                               <input name="reg_password" type="password" placeholder="Password" class="form-control" required>
                           </div>

                           <div class="form-group">
                               <input name="reg_name" type="text" placeholder="Nombre" class="form-control" required>
                           </div>

                           <div class="form-group">
                               <input name="reg_subname" type="text" placeholder="Apellidos" class="form-control" required>
                           </div>

                           <div class="form-group">
                               <input name="reg_email" type="email" placeholder="Email" class="form-control" required>
                           </div>

                           <button type="reg_submit" class="btn btn-primary pull-right">Registrar</button>

                      </form>  
                    </div>

                </div>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="login center">
                <div class="panel panel-secundary ">

                    <div class="panel-heading">
                    <h3 class="panel-title">Login</h3>
                    </div>

                    <div class="panel-body">
                       <form role="form" method="post" action="/modules/login/login.php">

                           <div class="form-group">
                               <input name="log_email" type="email" placeholder="Email" class="form-control" required>
                           </div>

                           <div class="form-group">
                               <input name="log_password" type="password" placeholder="Password" class="form-control" required>
                           </div>

                           <button type="log_submit" class="btn btn-success pull-right">Entrar</button>

                      </form>  
                    </div>

                </div>
            </div>    
        </div>
        <div class="clearfix visible-xs"></div>
      </div>
    </div>

      
    <?php include $root.View::$Footer; ?>
  </body>
</html>