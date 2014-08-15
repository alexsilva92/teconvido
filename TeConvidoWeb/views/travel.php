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
<html lang="es">
    <head>
        <?php include $_SERVER["DOCUMENT_ROOT"] . '/route.php'; ?>
        <?php include $root.View::$Head; ?>
        
        <?php $antsRoad = array("Viaje" => NULL); ?>
    </head>

    <body>
        <?php include $root.View::$Navbar; ?>

        <div class="container">
            <div class="container-teconvido">

                <?php include $root.Module::$AntsRoad?>
                
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h2 class="panel-title">Viaje</h2>
                    </div>
                    <div class="panel-body">
                   
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="well well-sm">
                                    <b>Origen: </b> <br><br> 
                                    <b>Destino: </b> <br><br> 
                                    <b>Fecha de salida: </b> <br><br> 
                                    <b>Duración aprox: </b> <br><br> 
                                    <b>Distancia: </b> <br><br> 
                                    <b>Coche: </b> <br><br> 
                                </div>
                            </div>
                            <div class="col-xs-6">
                                 <div class="panel panel-info">
                                    <div class="panel-heading">
                                        <h2 class="panel-title">Ruta</h2>
                                    </div>
                                    <div class="panel-body">
                                        <iframe
                                            width="410"
                                            height="185"
                                            frameborder="0" style="border:0"
                                            src="https://www.google.com/maps/embed/v1/directions?key=AIzaSyAGrDQ_kuUIaAti5dXsqPtDJ8QsgFvieW4&origin=TERUEL+Spain&destination=MORA DE RUBIELOS+Spain&avoid=tolls|highways">
                                        </iframe>
                                    </div>
                                 </div>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>    
        </div>

        <?php include $root.View::$Footer; ?>
    </body>
</html>