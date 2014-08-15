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
<div class="panel panel-primary">
    <div class="panel-heading">
        <h2 class="panel-title">Publicar Viaje</h2>
    </div>

    <div class="panel-body">
        <form action="<?=Action::$AddTravel?>" method="post">
            <div class="row">

                <?php include $this -> path.ViewPublishTravel::$CommonPanel?>

                <?php include $this -> path.ViewPublishTravel::$DriverPanel?>
            </div>
            <button type="submit" class="btn btn-primary btn-lg pull-right col-xs-4">Continuar y Publicar</button>    
        </form>
    </div>
</div>
<script src="/public/js/is_disabled.js"></script>



