NODE_IMAGE  = node:20-alpine
NPM         = docker run --rm -v $(PWD):/app -w /app $(NODE_IMAGE) npm

.PHONY: install test test-coverage check-cs fix-cs

install:
	$(NPM) install

test:
	$(NPM) test

test-coverage:
	$(NPM) run test:coverage

check-cs:
	$(NPM) run check-cs

fix-cs:
	$(NPM) run fix-cs
