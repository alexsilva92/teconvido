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

<div class="login center">
    <div class="panel panel-secundary ">

        <div class="panel-heading">
        <h3 class="panel-title">Login</h3>
        </div>

        <div class="panel-body">
           <form role="form" method="post" action="<?=Action::$StartSession?>">

               <div class="form-group">
                   <input name="nav_email" type="email" placeholder="Email" class="form-control" required>
               </div>

               <div class="form-group">
                   <input name="nav_password" type="password" placeholder="Password" class="form-control" required>
               </div>


               <button type="nav_submit" class="btn btn-success pull-right">Entrar</button>

          </form>  

          <a href="">¿Has olvidado tu contraseña?</a>

        </div>

    </div>
</div>  