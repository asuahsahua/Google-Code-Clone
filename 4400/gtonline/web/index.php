<?php


require_once(dirname(__FILE__).'/../config/ProjectConfiguration.class.php');

$configuration = ProjectConfiguration::getApplicationConfiguration('gtonline', 'dev', true);
sfContext::createInstance($configuration)->dispatch();
