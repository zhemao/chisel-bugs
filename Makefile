CXXFLAGS=-O1

export CXXFLAGS

TESTS := RightShift
OUTFILES := $(addprefix generated-src/,$(addsuffix .out,$(TESTS)))

default: $(OUTFILES)

generated-src/%.out: src/main/scala/*.scala
	mkdir -p generated-src
	sbt "run $(notdir $(basename $@)) --backend c --compile --genHarness --test --targetDir generated-src --debug" | tee $@

clean:
	rm -rf generated-src
