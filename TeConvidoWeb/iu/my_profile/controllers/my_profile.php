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

include_once $path.ModelMyProfile::$Menu;

class MyProfile{
    
    private $menus; 
    private $path;
    private $root;
    private $host;
    
    function MyProfile($root,$path,$host) {
        $this -> path = $path;
        $this -> root = $root;
        $this -> host = $host;
        
        $this -> menus = array();
    
        $this -> menus["my_travels"] = new Menu(Address::$MyTravels,
                "Mis Viajes",$path.ViewMyProfile::$MyTravelsPanel);
        
        $this -> menus["alert"] = new Menu(Address::$Alert,
                "Alertas",$path.ViewMyProfile::$AlertPanel);
        
        $this -> menus["my_cars"] = new Menu(Address::$MyCars,
                "Mis Coches",$path.ViewMyProfile::$MyCarsPanel);
        
        $this -> menus["my_comments"] = new Menu(Address::$MyComments,
                "Mis Comentarios",$path.ViewMyProfile::$MyCommentsPanel);
        
        $this -> menus["preferences"] = new Menu(Address::$PreferencesProfile,
                "Preferencias",$path.ViewMyProfile::$PreferencesPanel);
    }
    
    private function includeSection($root,$host){
        if(isset($_GET["section"])){
            $section = $_GET["section"];
            include $this -> menus[$section] -> panel;
        }else{
            include $this -> path.ViewMyProfile::$DefaultPanel;
        }
    }

    private function includeMenu(){
        foreach ($this -> menus as $section => $menu){
            if(isset($_GET["section"]) && strcmp($_GET["section"], $section) == 0){
                echo "<a class='menu-item selection' href=".$menu -> link.">".$menu -> title."</a>";
            }else{
                echo "<a class='menu-item' href=".$menu -> link.">".$menu -> title."</a>";
            }
        }
    }
    
    function includeBody(){
        $root = $this -> root;
        $host = $this -> host;
        ?>
        <div class="row">
            <div class="col-xs-3">
            <div class="menu-my_profile text-center">
                <img src="/public/images/user_150x150.png">
                <a href="<?=Address::$MyProfile?>"><h4><?=$_SESSION["login"]?></h4></a>
                <?php $this -> includeMenu(); ?>
            </div>
            </div>

            <div class="col-xs-9">
            <div class="section-my_profile">
                <div class="panel panel-success min-height-375">
                    <?php $this -> includeSection($root,$host); ?>
                </div>
            </div>
            </div>
        </div>
        <?php
    }
    
    function createAntsRoad(){
        $antsRoad = array ("Perfil" => Address::$MyProfile,$_SESSION["login"] => Address::$MyProfile);

        if(isset($_GET["section"])){
            $section = $_GET["section"];

            $antsRoad[$this -> menus[$section] -> title] = $this -> menus[$section] -> link;
        }

        return $antsRoad;
    }
}

