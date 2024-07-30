package Exercisechapter10.dataobjects;

public enum Menu {
      LOGIN("Login"),
      LOGOUT("Log out"),
      REGISTER("Register"),
      FAQ("FAQ"),
      BOOKTICKET("Book ticket"),
      TIMETABLE("Timetable"),
      MYTICKET("My ticket");


      private String description;

      Menu(String description) {
            this.description = description;
      }

      @Override
      public String toString() {
            return this.description;
      }
}
