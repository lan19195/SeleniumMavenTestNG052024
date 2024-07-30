package Exercisechapter10.dataobjects;

public class User {
      private String email;
      private String password;
      private String pid;

      public String getPid() {
            return pid;
      }

      public User(String email, String password) {
            this.email = email;
            this.password = password;
      }

      public User(String email, String password, String pid) {
            this.email = email;
            this.password = password;
            this.pid = pid;
      }

      public String getEmail() {
            return email;
      }

      public String getPassword() {
            return password;
      }

}
