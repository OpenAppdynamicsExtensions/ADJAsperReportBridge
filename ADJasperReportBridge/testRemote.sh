#!/bin/sh
rsync -v --dry-run --progress -r . appsphere:/home/dynamos/jasperreport.appsphere.org/updatesite/
