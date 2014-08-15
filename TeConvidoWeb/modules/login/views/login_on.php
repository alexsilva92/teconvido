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
    <a title="Cerrar Sesión" href="<?=Action::$CloseSession?>">
        <div class="nav-image navbar-right">
            <img src="/public/images/close_session_32x32.png"/>
        </div>
    </a>
</nav>

<nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
    <a href="<?=Address::$MyProfile?>">
    <div class="nav-user navbar-right">
            <img src="/public/images/user_150x150.png"/>
            <h5><?=$_SESSION["login"]?></h5>  
    </div>
    </a>      
</nav>

<nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
    <a title="Alertas" href="<?=Address::$Alert?>">
        <div class="nav-image navbar-right">
            <img src="/public/images/no_alert_32x32.png"/>
        </div>
    </a>
</nav>

<!--<nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
    <a title="Cerrar Sesión" href="/modules/login/close_session.php">
        <div class="panel-nav-image navbar-right">
            <img src="/public/images/no_message_32x32.png"/>
        </div>
    </a>
</nav>-->
