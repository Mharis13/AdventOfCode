public class Day01
{

    public static void part1()
    {

        int result = 0;

        List<List<int>> auxList = separateId();

        List<int> leftIdList = auxList[0];
        List<int> rightIdList = auxList[1];

        leftIdList.Sort();
        rightIdList.Sort();


        for (int i = 0; i < leftIdList.Count; i++)
        {
            var distance = 0;

            if (leftIdList[i] > rightIdList[i])
            {
                distance = leftIdList[i] - rightIdList[i];
            }
            else
            {
                distance = rightIdList[i] - leftIdList[i];
            }
            result += distance;
        }

        Console.WriteLine("Day1 Part1: " + result);

    }

    public static void part2()
    {

        int result = 0;


        List<List<int>> auxList = separateId();

        List<int> leftIdList = auxList[0];
        List<int> rightIdList = auxList[1];

        leftIdList.Sort();
        rightIdList.Sort();


        Dictionary<int, int> rigthListIdDict = new Dictionary<int, int>();

        foreach (var item in rightIdList)
        {
            if (rigthListIdDict.ContainsKey(item))
            {
                rigthListIdDict[item]++;
            }
            else
            {
                rigthListIdDict[item] = 1;
            }
        }

        foreach (var num in leftIdList)
        {
            var similarityScore = 1;
            if (rigthListIdDict.ContainsKey(num))
            {
                similarityScore = num * rigthListIdDict[num];
                result += similarityScore;
            }
        }
        Console.WriteLine("Day1 Part2: " + result);

    }


    public static List<List<int>> separateId()
    {
        var inputFile = @"C:\Users\mario\Desktop\AdventOfCode\Day01\Day01\input.txt";
        var auxList = new List<List<int>>();
        var leftIdList = new List<int>();
        var rightIdList = new List<int>();

        if (File.Exists(inputFile))
        {
            var lines = File.ReadAllLines(inputFile);

            foreach (var line in lines)
            {
                var ids = line.Split("   ");
                leftIdList.Add(int.Parse(ids[0]));
                rightIdList.Add(int.Parse(ids[1]));
            }

            auxList.Add(leftIdList);
            auxList.Add(rightIdList);
        }
        else
        {
            Console.WriteLine("El archivo no existe.");
        }

        return auxList;
    }


    static void Main(string[] args)
    {
        part1();
        part2();
    }
}