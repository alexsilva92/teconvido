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
        <h2 class="panel-title">Coche</h2>
    </div>
    <div class="panel-body">

        <div class="row">
            <div class="col-xs-6">
                <div class="well well-sm">
                    <b>Matrícula: </b><?=$this -> car -> carRegistration?><br><br> 
                    <b>Marca: </b><?=$this -> car -> brand?><br><br> 
                    <b>Modelo: </b><?=$this -> car -> model?><br><br> 
                    <b>Color: </b><?=$this -> car -> color?><br><br> 
                    <b>Descripcion:<?=$this -> car -> description?><br>
                    <textarea class="textarea-car"
                              style="overflow-y:scroll" readonly></textarea>
                </div>
            </div>
            <div class="col-xs-6">
                 <div class="panel panel-info">
                    <div class="panel-heading">
                        <h2 class="panel-title">Imagen</h2>
                    </div>
                    <div class="panel-body">
                        <div class="text-center">
                            <img class="img-car" src="/public/images/no_available_400x400.png">
                        </div>
                    </div>
                 </div>

            </div>
        </div>
    </div>
</div>