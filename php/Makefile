PHP_IMAGE          = php:8.1-cli
PHP_COVERAGE_IMAGE = php-8.1-pcov
COMPOSER           = docker run --rm -v $(PWD):/app -w /app composer:2
PHP                = docker run --rm -v $(PWD):/app -w /app $(PHP_IMAGE)
PHP_COVERAGE       = docker run --rm -v $(PWD):/app -w /app $(PHP_COVERAGE_IMAGE)

.PHONY: install update test build-coverage-image test-coverage

install:
	$(COMPOSER) install --no-interaction

update:
	$(COMPOSER) update --no-interaction

test:
	$(PHP) vendor/bin/phpunit

build-coverage-image:
	docker build -t $(PHP_COVERAGE_IMAGE) -f Dockerfile.coverage . -q

test-coverage: build-coverage-image
	$(PHP_COVERAGE) php -d pcov.enabled=1 vendor/bin/phpunit --coverage-text --coverage-html build/coverage
