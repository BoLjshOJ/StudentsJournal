insert into usr(id, username, password, is_active)
  values (996, 'user', 'user', true),
         (997, 'user2', 'user2', true),
         (998, 'user3', 'user3', true);

insert into user_role (user_id, roles)
  values (996, 'USER'),
         (997, 'USER'),
         (998, 'USER');

insert into student (id, failed_runs_of_test, full_name, group_name, success_runs_of_test, total_test, user_id)
  values (997, 0, 'Александр Пушкин', 'Сбербанк', 5, 5, 996),
         (998, 3, 'Владимир Ленин', 'Сбербанк', 2, 5, 997),
         (999, 5, 'Дмитрий Медведев', 'ВТБ24', 0, 5, 998);

insert into task (id, file_location, is_resolved, task_name, student_id)
  values (985, 'com.studentsJournal.lessons.lesson1', true, 'Task1', 997),
         (986, 'com.studentsJournal.lessons.lesson2', true, 'Task2', 997),
         (987, 'com.studentsJournal.lessons.lesson3', true, 'Task3', 997),
         (988, 'com.studentsJournal.lessons.lesson4', true, 'Task4', 997),
         (989, 'com.studentsJournal.lessons.lesson5', true, 'Task5', 997),
         (990, 'com.studentsJournal.lessons.lesson1', true, 'Task1', 998),
         (991, 'com.studentsJournal.lessons.lesson2', false, 'Task2', 998),
         (992, 'com.studentsJournal.lessons.lesson3', false, 'Task3', 998),
         (993, 'com.studentsJournal.lessons.lesson4', false, 'Task4', 998),
         (994, 'com.studentsJournal.lessons.lesson5', true, 'Task5', 998),
         (995, 'com.studentsJournal.lessons.lesson1', false, 'Task1', 999),
         (996, 'com.studentsJournal.lessons.lesson2', false, 'Task2', 999),
         (997, 'com.studentsJournal.lessons.lesson3', false, 'Task3', 999),
         (998, 'com.studentsJournal.lessons.lesson4', false, 'Task4', 999),
         (999, 'com.studentsJournal.lessons.lesson5', false, 'Task5', 999);
