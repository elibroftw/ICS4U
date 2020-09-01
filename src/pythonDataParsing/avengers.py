import csv

# with open('avengers.csv', 'r') as f:
#     csv_reader = csv.reader(f, quotechar='|')
#     for row in csv_reader:
#         print(list(row))

highschooldegree = 0
lastnameM=0
salary = 0
losers = 0

with open('data.csv', 'r') as f:
    csv_reader = csv.reader(f)
    numOfPeople = -1
    for row in csv_reader:
        numOfPeople += 1
        if numOfPeople == 0:
            continue
        person = list(row)
        specific_salary = float(person[-1][1:].replace(',', ''))
        salary += specific_salary
        if person[2].lower() == 'high school':
            highschooldegree += 1
        if person[0].startswith('M'):
            lastnameM += 1
        if int(person[3]) > 15 and specific_salary > 50000:
            losers += 1
print('Average salary:', salary/numOfPeople)
print('Highest education being High School:', highschooldegree)
print('Last names starting with M:', lastnameM)
print('More than 15 years but less than 50k:', losers)
