<?php

/**
 * BaseSchool
 * 
 * This class has been auto-generated by the Doctrine ORM Framework
 * 
 * @property integer $id
 * @property string $name
 * @property integer $school_type_id
 * @property SchoolType $SchoolType
 * @property Doctrine_Collection $UserSchool
 * 
 * @method integer             getId()             Returns the current record's "id" value
 * @method string              getName()           Returns the current record's "name" value
 * @method integer             getSchoolTypeId()   Returns the current record's "school_type_id" value
 * @method SchoolType          getSchoolType()     Returns the current record's "SchoolType" value
 * @method Doctrine_Collection getUserSchool()     Returns the current record's "UserSchool" collection
 * @method School              setId()             Sets the current record's "id" value
 * @method School              setName()           Sets the current record's "name" value
 * @method School              setSchoolTypeId()   Sets the current record's "school_type_id" value
 * @method School              setSchoolType()     Sets the current record's "SchoolType" value
 * @method School              setUserSchool()     Sets the current record's "UserSchool" collection
 * 
 * @package    gtonline
 * @subpackage model
 * @author     Your name here
 * @version    SVN: $Id: Builder.php 7490 2010-03-29 19:53:27Z jwage $
 */
abstract class BaseSchool extends sfDoctrineRecord
{
    public function setTableDefinition()
    {
        $this->setTableName('school');
        $this->hasColumn('id', 'integer', null, array(
             'type' => 'integer',
             'primary' => true,
             'autoincrement' => true,
             ));
        $this->hasColumn('name', 'string', 255, array(
             'type' => 'string',
             'unique' => true,
             'notnull' => true,
             'length' => 255,
             ));
        $this->hasColumn('school_type_id', 'integer', null, array(
             'type' => 'integer',
             'notnull' => true,
             ));
    }

    public function setUp()
    {
        parent::setUp();
        $this->hasOne('SchoolType', array(
             'local' => 'school_type_id',
             'foreign' => 'id'));

        $this->hasMany('UserSchool', array(
             'local' => 'id',
             'foreign' => 'schoolId'));
    }
}