class ForumPost < ActiveRecord::Base
  belongs_to :user
  belongs_to :ForumTopic
end
