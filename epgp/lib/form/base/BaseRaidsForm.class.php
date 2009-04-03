<?php

/**
 * Raids form base class.
 *
 * @package    epgp
 * @subpackage form
 * @author     Your name here
 * @version    SVN: $Id: sfPropelFormGeneratedTemplate.php 15484 2009-02-13 13:13:51Z fabien $
 */
class BaseRaidsForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'          => new sfWidgetFormInputHidden(),
      'note'        => new sfWidgetFormInput(),
      'boss'        => new sfWidgetFormPropelChoice(array('model' => 'Bosses', 'add_empty' => false)),
      'baseval'     => new sfWidgetFormInput(),
      'inflatedval' => new sfWidgetFormInput(),
      'date'        => new sfWidgetFormDateTime(),
    ));

    $this->setValidators(array(
      'id'          => new sfValidatorPropelChoice(array('model' => 'Raids', 'column' => 'id', 'required' => false)),
      'note'        => new sfValidatorString(array('max_length' => 512)),
      'boss'        => new sfValidatorPropelChoice(array('model' => 'Bosses', 'column' => 'id')),
      'baseval'     => new sfValidatorNumber(),
      'inflatedval' => new sfValidatorNumber(),
      'date'        => new sfValidatorDateTime(),
    ));

    $this->widgetSchema->setNameFormat('raids[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Raids';
  }


}