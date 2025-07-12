module main.enterprise {
//    exports main.enterprise;
    requires main.api;
    uses main.api.Accountant;
    requires main.university;
    requires main.labor.market;
}