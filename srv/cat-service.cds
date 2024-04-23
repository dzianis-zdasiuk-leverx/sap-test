using com.leverx.dzianis.zdasiuk as my from '../db/models/car-model';

service CatalogService {
    @readonly entity Car as projection on my.Car;
}