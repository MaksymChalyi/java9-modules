
module main.university {
    requires main.api;
//    opens main.university;
    provides main.api.Accountant
            with main.university.AccountantImpl;
}