create table forecast (
                          forecast_id bigint not null auto_increment,
                          forecast_category varchar(255),
                          forecast_date date,
                          forecast_time time,
                          measure_value varchar(255),
                          town_id bigint,
                          primary key (forecast_id)
) engine=InnoDB;

create table forecast_location (
                                   forecast_location_id bigint not null auto_increment,
                                   address_detail varchar(255),
                                   city varchar(255),
                                   location_x varchar(255),
                                   location_y varchar(255),
                                   state varchar(255),
                                   primary key (forecast_location_id)
) engine=InnoDB;

create table member (
                        member_id bigint not null,
                        password varchar(255),
                        slack_token_key varchar(255),
                        username varchar(255),
                        primary key (member_id)
) engine=InnoDB;

create table notification_event (
                                    notification_event_id bigint not null auto_increment,
                                    event_target_table_id bigint not null,
                                    event_type varchar(255),
                                    notification_time time,
                                    notification_type varchar(255),
                                    send_friday bit not null,
                                    send_monday bit not null,
                                    send_saturday bit not null,
                                    send_sunday bit not null,
                                    send_thursday bit not null,
                                    send_tues_day bit not null,
                                    send_wednesday bit not null,
                                    member_id bigint,
                                    primary key (notification_event_id)
) engine=InnoDB;