create table if not exists winter_market_1.categories
(
    id bigint auto_increment
    primary key,
    description varchar(255) null,
    title varchar(255) null
    )
    engine=MyISAM;

create table if not exists winter_market_1.delivery_addresses
(
    id bigint auto_increment
    primary key,
    address varchar(255) null,
    user_id bigint null
    )
    engine=MyISAM;

create index FK5lv80e9vimtc9usl7x4sfbnpn
    on winter_market_1.delivery_addresses (user_id);

create table if not exists winter_market_1.orders
(
    id bigint auto_increment
    primary key,
    create_at datetime null,
    delivery_date datetime null,
    delivery_price double null,
    phone_number varchar(255) null,
    price double null,
    update_at datetime null,
    delivery_address_id bigint null,
    status_id bigint null,
    user_id bigint null
    )
    engine=MyISAM;

create index FK32ql8ubntj5uh44ph9659tiih
    on winter_market_1.orders (user_id);

create index FK517vqfpcs3tncyw22i0x7ikbp
    on winter_market_1.orders (status_id);

create index FK6ofjhra6o7py55ky4pcdqre1t
    on winter_market_1.orders (delivery_address_id);

create table if not exists winter_market_1.orders_item
(
    id bigint auto_increment
    primary key,
    item_price double null,
    quantity bigint null,
    total_price double null,
    order_id bigint null,
    product_id bigint null
)
    engine=MyISAM;

create index FKqa7i0ev3xqm2d6t93n9blxef1
    on winter_market_1.orders_item (order_id);

create index FKriu2y66bu5b7l5f08pbrngfqv
    on winter_market_1.orders_item (product_id);

create table if not exists winter_market_1.orders_statuses
(
    id bigint auto_increment
    primary key,
    title varchar(255) null
    )
    engine=MyISAM;

create table if not exists winter_market_1.products
(
    id bigint auto_increment
    primary key,
    create_at datetime null,
    full_description varchar(255) null,
    price double not null,
    short_description varchar(255) null,
    title varchar(250) not null,
    update_at datetime null,
    vendor_code varchar(8) not null,
    category_id bigint not null
    )
    engine=MyISAM;

create index FKog2rp4qthbtt2lfyhfo32lsw9
    on winter_market_1.products (category_id);

create table if not exists winter_market_1.products_images
(
    id bigint auto_increment
    primary key,
    path varchar(255) null,
    product_id bigint null
    )
    engine=MyISAM;

create index FKgt41wyswrex82sy6iwdup2eak
    on winter_market_1.products_images (product_id);

create table if not exists winter_market_1.roles
(
    id bigint auto_increment
    primary key,
    name varchar(255) null
    )
    engine=MyISAM;

create table if not exists winter_market_1.users
(
    id bigint auto_increment
    primary key,
    email varchar(255) null,
    first_name varchar(255) null,
    last_name varchar(255) null,
    password varchar(255) null,
    username varchar(255) null
    )
    engine=MyISAM;

create table if not exists winter_market_1.users_roles
(
    user_id bigint not null,
    role_id bigint not null
)
    engine=MyISAM;

create index FK2o0jvgh89lemvvo17cbqvdxaa
    on winter_market_1.users_roles (user_id);

create index FKj6m8fwv7oqv74fcehir1a9ffy
    on winter_market_1.users_roles (role_id);