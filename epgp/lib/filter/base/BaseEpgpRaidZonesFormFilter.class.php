<?php

require_once(sfConfig::get('sf_lib_dir').'/filter/base/BaseFormFilterPropel.class.php');

/**
 * EpgpRaidZones filter form base class.
 *
 * @package    epgp
 * @subpackage filter
 * @author     Your name here
 * @version    SVN: $Id: sfPropelFormFilterGeneratedTemplate.php 15484 2009-02-13 13:13:51Z fabien $
 */
class BaseEpgpRaidZonesFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'name' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'name' => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('epgp_raid_zones_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'EpgpRaidZones';
  }

  public function getFields()
  {
    return array(
      'id'   => 'Number',
      'name' => 'Text',
    );
  }
}
