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
<nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
    <form class="navbar-form navbar-right" role="form" method="post"
          action="<?=Address::$Register?>">
      <button type="submit" class="btn btn-primary">Registro</button>
    </form>
</nav>

<nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
    <form class="navbar-form navbar-right" role="form" method="post" 
          action="<?=Action::$StartSession?>">
      <div class="form-group"> 
          <input class="form-control input-user" name="nav_email" type="email" placeholder="Email"  required>
      </div>
      <div class="form-group">
        <input class="form-control input-password" name="nav_password" type="password" placeholder="Contraseņa"  required>
      </div>
      <button type="submit" class="btn btn-success">Entrar</button>
    </form>
</nav>