begin;

insert into team_management.address values (1, 'Willingdon, England');
insert into team_management.address values (2, 'Far away');
insert into team_management.address values (3, 'House');
insert into team_management.address values (4, 'Barn');

insert into team_management.company values (1, 'Manor Farm');

insert into team_management.team values (1, 'Human', 1);
insert into team_management.team values (2, 'Animals', 1);
insert into team_management.team values (3, 'Animals more equal than others', 1);

insert into team_management.office values (1, 'Animal Farm', 1, 1);
insert into team_management.office values (2, 'House', 1, 3);
insert into team_management.office values (3, 'Barn', 1, 4);

insert into team_management.employee values (1, 'Mr. Jones', 2, 1, null);
insert into team_management.employee values (2, 'Old Major', 1, 1, null);
insert into team_management.employee values (3, 'Napoleon', 1, 1, 2);
insert into team_management.employee values (4, 'Snowball', 2, 1, 1);
insert into team_management.employee values (5, 'Boxer', 4, 1, 3);

insert into team_management.employee_team values (1, 1, 1);
insert into team_management.employee_team values (2, 2, 3);
insert into team_management.employee_team values (3, 3, 2);
insert into team_management.employee_team values (4, 3, 3);
insert into team_management.employee_team values (5, 4, 2);
insert into team_management.employee_team values (6, 5, 2);

commit;