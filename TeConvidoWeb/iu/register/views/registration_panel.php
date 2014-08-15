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

<div class="register">
    <div class="panel panel-primary ">

        <div class="panel-heading">
        <h3 class="panel-title">Registro</h3>
        </div>

        <div class="panel-body">
            <form role="form" method="post" action="<?=Action::$Register?>">

               <div class="form-group">
                   <input name="reg_login" type="text" placeholder="Login" class="form-control" autocomplete="off" required 
                     value="<?php  if(isset($_POST['reg_login'])) echo $_POST['reg_login']?>">
               </div> 

               <div class="form-group">
                   <input name="reg_password" type="password" placeholder="Password" class="form-control" autocomplete="off" required>
               </div>

               <div class="form-group">
                   <input name="reg_name" type="text" placeholder="Nombre" class="form-control" required
                      value="<?php  if(isset($_POST['reg_name'])) echo $_POST['reg_name']?>">
               </div>

               <div class="form-group">
                   <input name="reg_subname" type="text" placeholder="Apellidos" class="form-control" required
                      value="<?php  if(isset($_POST['reg_subname'])) echo $_POST['reg_subname']?>">
               </div>

               <div class="form-group">
                   <input name="reg_email" type="email" placeholder="Email" class="form-control" required
                      value="<?php  if(isset($_POST['reg_email'])) echo $_POST['reg_email']?>">
               </div>       
               <button type="submit" class="btn btn-primary pull-right">Registrar</button>

          </form>  
        </div>

    </div>
</div>
