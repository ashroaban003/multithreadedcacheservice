ex.scheduleAtFixedRate(
            () -> System.out.println("Task executed after 5 second delay!"),
            5,
            10,
            TimeUnit.SECONDS);