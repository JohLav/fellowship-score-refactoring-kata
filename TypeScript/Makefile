NODE_IMAGE  = node:18-alpine
NPM         = docker run --rm -v $(PWD):/app -w /app $(NODE_IMAGE) npm

.PHONY: install test test-coverage

install:
	$(NPM) install

test:
	$(NPM) test

test-coverage:
	$(NPM) run test:coverage
