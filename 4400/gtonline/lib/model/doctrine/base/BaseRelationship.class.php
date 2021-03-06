<?php

/**
 * BaseRelationship
 * 
 * This class has been auto-generated by the Doctrine ORM Framework
 * 
 * @property integer $sourceUserId
 * @property integer $destinationUserId
 * @property string $description
 * @property date $acceptTime
 * @property integer $status
 * @property User $SourceUser
 * @property User $DestinationUser
 * 
 * @method integer      getSourceUserId()      Returns the current record's "sourceUserId" value
 * @method integer      getDestinationUserId() Returns the current record's "destinationUserId" value
 * @method string       getDescription()       Returns the current record's "description" value
 * @method date         getAcceptTime()        Returns the current record's "acceptTime" value
 * @method integer      getStatus()            Returns the current record's "status" value
 * @method User         getSourceUser()        Returns the current record's "SourceUser" value
 * @method User         getDestinationUser()   Returns the current record's "DestinationUser" value
 * @method Relationship setSourceUserId()      Sets the current record's "sourceUserId" value
 * @method Relationship setDestinationUserId() Sets the current record's "destinationUserId" value
 * @method Relationship setDescription()       Sets the current record's "description" value
 * @method Relationship setAcceptTime()        Sets the current record's "acceptTime" value
 * @method Relationship setStatus()            Sets the current record's "status" value
 * @method Relationship setSourceUser()        Sets the current record's "SourceUser" value
 * @method Relationship setDestinationUser()   Sets the current record's "DestinationUser" value
 * 
 * @package    gtonline
 * @subpackage model
 * @author     Your name here
 * @version    SVN: $Id: Builder.php 7490 2010-03-29 19:53:27Z jwage $
 */
abstract class BaseRelationship extends sfDoctrineRecord
{
    public function setTableDefinition()
    {
        $this->setTableName('relationship');
        $this->hasColumn('sourceUserId', 'integer', null, array(
             'type' => 'integer',
             'primary' => true,
             ));
        $this->hasColumn('destinationUserId', 'integer', null, array(
             'type' => 'integer',
             'primary' => true,
             ));
        $this->hasColumn('description', 'string', 255, array(
             'type' => 'string',
             'length' => 255,
             ));
        $this->hasColumn('acceptTime', 'date', null, array(
             'type' => 'date',
             ));
        $this->hasColumn('status', 'integer', null, array(
             'type' => 'integer',
             'notnull' => true,
             ));
    }

    public function setUp()
    {
        parent::setUp();
        $this->hasOne('User as SourceUser', array(
             'local' => 'sourceUserId',
             'foreign' => 'id'));

        $this->hasOne('User as DestinationUser', array(
             'local' => 'destinationUserId',
             'foreign' => 'id'));
    }
}