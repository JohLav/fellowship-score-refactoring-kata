NODE_IMAGE  = node:18-alpine
NPM         = docker run --rm -v $(PWD):/app -w /app $(NODE_IMAGE) npm

.PHONY: install test test-coverage test-jest test-mocha

install:
	$(NPM) install

test:
	$(NPM) test

test-coverage:
	$(NPM) run test:coverage

test-jest:
	$(NPM) run test:jest

test-mocha:
	$(NPM) run test:mocha
