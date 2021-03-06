<?php

/**
 * BaseUserEmployer
 * 
 * This class has been auto-generated by the Doctrine ORM Framework
 * 
 * @property integer $id
 * @property integer $userId
 * @property integer $employerId
 * @property string $jobTitle
 * @property User $User
 * @property Employer $Employer
 * 
 * @method integer      getId()         Returns the current record's "id" value
 * @method integer      getUserId()     Returns the current record's "userId" value
 * @method integer      getEmployerId() Returns the current record's "employerId" value
 * @method string       getJobTitle()   Returns the current record's "jobTitle" value
 * @method User         getUser()       Returns the current record's "User" value
 * @method Employer     getEmployer()   Returns the current record's "Employer" value
 * @method UserEmployer setId()         Sets the current record's "id" value
 * @method UserEmployer setUserId()     Sets the current record's "userId" value
 * @method UserEmployer setEmployerId() Sets the current record's "employerId" value
 * @method UserEmployer setJobTitle()   Sets the current record's "jobTitle" value
 * @method UserEmployer setUser()       Sets the current record's "User" value
 * @method UserEmployer setEmployer()   Sets the current record's "Employer" value
 * 
 * @package    gtonline
 * @subpackage model
 * @author     Your name here
 * @version    SVN: $Id: Builder.php 7490 2010-03-29 19:53:27Z jwage $
 */
abstract class BaseUserEmployer extends sfDoctrineRecord
{
    public function setTableDefinition()
    {
        $this->setTableName('user_employer');
        $this->hasColumn('id', 'integer', null, array(
             'type' => 'integer',
             'primary' => true,
             'autoincrement' => true,
             ));
        $this->hasColumn('userId', 'integer', null, array(
             'type' => 'integer',
             'notnull' => true,
             ));
        $this->hasColumn('employerId', 'integer', null, array(
             'type' => 'integer',
             'notnull' => true,
             ));
        $this->hasColumn('jobTitle', 'string', 255, array(
             'type' => 'string',
             'notnull' => true,
             'length' => 255,
             ));


        $this->index('uniqueness', array(
             'fields' => 
             array(
              0 => 'userId',
              1 => 'employerId',
              2 => 'jobTitle',
             ),
             ));
    }

    public function setUp()
    {
        parent::setUp();
        $this->hasOne('User', array(
             'local' => 'userId',
             'foreign' => 'id'));

        $this->hasOne('Employer', array(
             'local' => 'employerId',
             'foreign' => 'id'));
    }
}