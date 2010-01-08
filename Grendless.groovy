def runner = new GrendlessRunner(System.out)

for(;;) {
	sleep 1000
	runner.run(new File(args[0]))
}