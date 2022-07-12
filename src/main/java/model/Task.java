package model;
import java.util.Date;

    public class Task {

        private int id;
        private String username;
        private String task;
        private Date deadline;
        private boolean completion;


        public Task(int id, String username, String task, Date deadline, boolean completion) {
            this.id = id;
            this.username = username;
            this.task = task;
            this.deadline = deadline;
            this.completion = completion;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }


        public String getTask() {
            return task;
        }

        public Date getDeadline() {
            return deadline;
        }

        public boolean isCompletion() {
            return completion;
        }

        @Override
        public String toString() {
            return "Task: " +
                    " id: " + id +
                    ", name: " + username +
                    ", task: " + task  +
                    ", deadline is: " + deadline +
                    ", task completed: " + completion;
        }
    }


