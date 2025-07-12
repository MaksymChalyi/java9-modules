module main.labor.market {
    requires  main.api;
    provides  main.api.Accountant
            with main.labor.market.AccountantLMImpl;
}